(ns mirth.dictionary)

(def dictionary (ref {}))

(defn exception-word-not-defined [name]
  (throw (Exception. (format "Word not defined: \"%s\"" name))))

(defn exception-word-already-defined [name]
  (throw (Exception. (format "Word already defined: \"%s\"" name))))

(defn- add-word* [name f immediate?]
  (dosync (alter dictionary assoc name {:fn f :immediate? immediate?})))

(defn add-word
  ([name f]
     (add-word name f false))
  ([name f immediate?]
     (if (@dictionary name)
       (exception-word-already-defined name)
       (add-word* name f immediate?))))

(defn remove-word* [name]
  (dosync (alter dictionary dissoc name)))

(defn remove-word [name]
  (if (@dictionary name)
    (remove-word* name)
    (exception-word-not-defined name)))

(defn alias-word [name orig]
  (if-let [word (@dictionary orig)]
    (add-word name (:fn word) (boolean (:immediate word)))
    (exception-word-not-defined name)))

(defn get-word [name]
  (or (@dictionary name)
      (exception-word-not-defined name)))

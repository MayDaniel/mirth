(ns mirth.reader
  (:import [java.util Scanner]
           [java.util.regex Pattern]))

(def reader (atom 0))

(defn read-repl []
  (swap! reader (constantly (Scanner. (read-line)))))

(defn read-file [file]
  (swap! reader (constantly (Scanner. (slurp file)))))

(defn- next-word []
  (cond
   (.hasNextInt @reader)     (.nextInt @reader)
   (.hasNextFloat @reader)   (.nextFloat @reader)
   (.hasNextBoolean @reader) (.nextBoolean @reader)
   (.hasNext @reader)        (.next @reader)
   :else ""))

(defn read-word []
  (let [word (next-word)]
    (if (and (string? word) (re-matches #"|\s" word))
      :EOF word)))

(defn read-str [delim]
  (let [original-delim  (.delimiter @reader)
        temporary-delim (Pattern/quote delim)]
    (.skip @reader #"\s")
    (.useDelimiter @reader temporary-delim)
    (let [string (read-word)]
      (.skip @reader temporary-delim)
      (.useDelimiter @reader original-delim)
      string)))

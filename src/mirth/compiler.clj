(ns mirth.compiler
  (:use [mirth.reader     :only [read-word]]
        [mirth.dictionary :only [get-word]]
        [mirth.stack      :only [push-stack]]))

(defn compile-words [words]
  (reduce (fn [words w] (let [word-def (get-word w)]
                          (if (:immediate? word-def)
                            (do ((:fn word-def)) words)
                            (conj words word-def))))
          [] words))

(defn read-definition []
  (doall (take-while
          (complement #{";" :EOF})
          (repeatedly read-word))))

(defn read-var []
  (repeatedly 2 read-word))

(defn evaluate* []
  (let [word (read-word)]
    (cond (string? word)   (do ((:fn (get-word word))) (recur))
          (not= word :EOF) (do (push-stack word) (recur)))))

(defn evaluate []
  (try (evaluate*)
       (catch Exception e (println (.getMessage e)))))

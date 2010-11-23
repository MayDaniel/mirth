(ns mirth.mirth
  (:use [mirth.reader    :only [read-repl read-file]]
        [mirth.compiler  :only [evaluate]]
        [mirth.functions :only [dictionary-defaults]]))

(def *mirth-version* "0.1.0-SNAPSHOT")
(def prompt "> ")

(defn mirth-repl []
  (println "Mirth REPL")
  (println "Mirth Version:" *mirth-version*)
  (while true
    (print prompt)
    (flush)
    (read-repl)
    (evaluate)))

(defn mirth-file [file]
  (read-file file)
  (evaluate))

(defn mirth []
  (dictionary-defaults)
  (if-let [files (seq *command-line-args*)]
    (dorun (map mirth-file files))
    (mirth-repl)))

(mirth)

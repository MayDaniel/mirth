(ns mirth.stack)

(def stack (atom []))

(defn peek-stack []
  (peek @stack))

(defn pop-stack []
  (let [value (peek-stack)]
    (swap! stack pop)
    value))

(defn push-stack [value]
  (swap! stack conj value))

(defn swap-stack []
  (let [a (pop-stack) b (pop-stack)]
    (push-stack a) (push-stack b)))

(defn clear-stack []
  (swap! stack (constantly [])))

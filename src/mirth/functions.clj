(ns mirth.functions  
  (:use [mirth.stack      :only [stack pop-stack push-stack peek-stack clear-stack swap-stack]]
        [mirth.reader     :only [reader read-str]]
        [mirth.compiler   :only [read-definition read-var compile-words]]
        [mirth.dictionary :only [add-word remove-word alias-word]]))

(defn dictionary-defaults []

  (add-word "var"     #(let [[name value] (read-var)]
                         (add-word name (fn [] (push-stack value)))))
  (add-word "def"     #(let [[name & code] (read-definition)]
                         (add-word name (fn [] (doseq [word (compile-words code)]
                                                 ((:fn word)))))))
  (add-word "undef"   #(remove-word (pop-stack)))
  (add-word "alias"   #(alias-word (pop-stack) (pop-stack)))
  
  (add-word "nil"     #(push-stack nil))
  (add-word "boolean" #(push-stack (boolean (pop-stack))))
  (add-word "and"     #(push-stack (and (pop-stack) (pop-stack))))
  (add-word "or"      #(push-stack (or (pop-stack) (pop-stack))))
  (add-word "not"     #(push-stack (not (pop-stack))))
  
  (add-word "swap"    #(swap-stack))
  (add-word "dup"     #(push-stack (peek-stack)))
  
  (add-word "+"       #(push-stack (+ (pop-stack) (pop-stack))))
  (add-word "*"       #(push-stack (* (pop-stack) (pop-stack))))
  (add-word "/"       #(do (swap-stack) (push-stack (/ (pop-stack) (pop-stack)))))
  (add-word "-"       #(do (swap-stack) (push-stack (- (pop-stack) (pop-stack)))))
  (add-word "="       #(push-stack (= (pop-stack) (pop-stack))))
  (add-word "=="      #(push-stack (== (pop-stack) (pop-stack))))
  (add-word "<"       #(push-stack (> (pop-stack) (pop-stack))))
  (add-word ">"       #(push-stack (< (pop-stack) (pop-stack))))
  
  (add-word ".("      #(push-stack (read-str ")")))
  
  (add-word "."       #(println (pop-stack)))
  (add-word ".S"      #(println @stack))

  (add-word ";"       #())
  
  (add-word "clear"   #(clear-stack))
  
  (add-word "//"      #(.nextLine @reader) true)
  
  (add-word "exit"    #(System/exit 0)))

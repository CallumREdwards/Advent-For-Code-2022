(ns advent-of-code.day6) 

(defn index-of-first [pred coll]
  (->> (map-indexed vector coll)
       (filter #(pred (second %)))
       (map first)
       first))

; fist part used 4 not 14
(->> (slurp "data/input6.txt")
     (partition 14 1)
     (index-of-first (partial apply distinct?))
     (+ 14))


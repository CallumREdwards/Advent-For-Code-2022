(ns advent-of-code.day5 
  (:require [clojure.string :as str])


(defn index-of-first [pred coll]
  (->> (map-indexed vector coll)
       (filter #(pred (second %)))
       (map first)
       first))


(->> (slurp "data/input6.txt")
     (partition 14 1)
     (index-of-first (partial apply distinct?))
     (+ 14))


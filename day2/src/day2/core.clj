(ns day2.core
  (:require [clojure.string :as str]))

(defn score1 [opp you]
  (case you
    "X" (inc (case opp
               "A" 3
               "B" 0
               "C" 6))
    "Y" (+ 2 (case opp
               "A" 6
               "B" 3
               "C" 0))         
    "Z" (+ 3 (case opp
               "A" 0
               "B" 6
               "C" 3))))

(defn getGuide []
  (->> (slurp "/home/callume/advent_of_code/day2/data/input2.txt")
       str/split-lines
       (map #(str/split % #" "))))

(defn task1 []
  (->> (getGuide)
       (map (partial apply score1))
       (reduce +)))

(task1)

(defn score2 [opp result]
  (case result
    "X" (case opp
          "A" 3
          "B" 1 
          "C" 2)
    "Y" (+ 3 (case opp
               "A" 1
               "B" 2
               "C" 3))         
    "Z" (+ 6 (case opp
               "A" 2
               "B" 3
               "C" 1))))

(defn task2 []
  (->> (getGuide)
       (map (partial apply score2))
       (reduce +)))

(task2)


(ns advent-of-code.day4
  (:require [clojure.string :as str]))
  
(defrecord Range [start end])

(defn getRange [txt]
  (->> (str/split txt #"-")
       (map read-string)
       (apply ->Range)))

(defn getRanges [line]
  (->> (str/split line #",")
       (map getRange)))

(defn fullyContains? [range1 range2]
  "returns true if range 1 fully contains range 2"
  (and
    (<= (:start range1) (:start range2))
    (>= (:end range1) (:end range2))))

(defn eitherFullyContains? [range1 range2]
  "returns true if either range fully contains the other"
  (or (fullyContains? range1 range2) (fullyContains? range2 range1)))

(defn countTrue [xs]
  (count (filter identity xs)))

(defn solution1 []
  (->> (slurp "data/input4.txt")
       str/split-lines
       (map getRanges)
       (map (partial apply eitherFullyContains?))
       (countTrue)))

(solution1)

(defn within? [x r]
  "returns true if x within range r"
  (and (>= x (:start r)) (<= x (:end r)))) 
    

(defn overlaps? [r1 r2]
  "returns true if ranges overlap each other"
  (or
    (fullyContains? r1 r2)
    (or (within? (:start r1) r2) (within? (:end r1) r2))))

(defn solution2 []
  (->> (slurp "data/input4.txt")
       str/split-lines
       (map getRanges)
       (map (partial apply overlaps?))
       (countTrue)))

(solution2)

(ns day3.core)

(defn calculatePriority [letter]
  (let [intValue (int letter)]
    (- intValue (if (> intValue 96) 96 38))))

(defn splitInTwo [line]
  (split-at (/ (count line) 2) line)) 

(defn firstIntersect [xs]
  (->> (map set xs)
       (apply clojure.set/intersection)
       first))

(defn getElfPriority [line]
  (->> (splitInTwo line)
       firstIntersect
       calculatePriority))

(defn solution1 []
  (->> (slurp "data/input.txt")
       clojure.string/split-lines
       (map getElfPriority)
       (reduce +)))

(defn getGroupPriority [elves]
  (calculatePriority (firstIntersect elves)))

(defn solution2 []
  (->> (slurp "data/input.txt")
       clojure.string/split-lines
       (partition 3)
       (map getGroupPriority)
       (reduce +)))

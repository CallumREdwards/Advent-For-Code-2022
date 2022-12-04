#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn splitOnBlankLine [fileName]
  (str/split (slurp fileName) #"\n\n"))

(defn getCalorieSum [elfCalorie]
  (->> (str/split-lines elfCalorie)
       (map read-string)
       (reduce +)))	

(let [[inputFile numElves] *command-line-args*
      elfCalories (splitOnBlankLine inputFile)]
  (->> (map getCalorieSum elfCalories) 
       (sort >)
       (take (read-string numElves))
       (reduce +)))
	

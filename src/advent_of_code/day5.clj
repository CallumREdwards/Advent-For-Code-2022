(ns advent-of-code.day5
  (:require [clojure.string :as str]))

(defrecord Step [amount fromIndex toIndex])

(defn move [amount fromStack toStack]
  (let [[packages remaining] (split-at amount fromStack)]
    [remaining (concat (reverse packages) toStack)]))

(defn replace [n x xs]
  "reorder args of assoc so we can use '->>' nicely in runStep"
  (assoc xs n x))

(defn runStep [stacks step]
  (let [fromStack (get stacks (:fromIndex step))
        toStack (get stacks (:toIndex step))
        [fromStack' toStack'] (move (:amount step) fromStack toStack)]
    (->> stacks
         (replace (:fromIndex step) fromStack')
         (replace (:toIndex step) toStack'))))

; Couldn't be bothered to parse starting state
(def startingStacks
  [[\J \F \C \N \D \B \W]
   [\T \S \L \Q \V \Z \P]
   [\T \J \G \B \Z \P]
   [\C \H \B \Z \J \L \T \D]
   [\S \J \B \V \G]
   [\Q \S \P]
   [\N \P \M \L \F \D \V \B]
   [\R \L \D \B \F \M \S \P]
   [\R \T \D \V]])

(defn parseStep [line]
  (let [splitLine (str/split line #" ")]
    (Step.
      (read-string (get splitLine 1))
      (dec (read-string (get splitLine 3)))
      (dec (read-string (get splitLine 5)))))) 

(defn getSteps []
  (->> (slurp "data/input5.txt")
       (#(str/split % #"\n\n"))
       last
       str/split-lines
       (map parseStep)))
      

;;debugging parts of expressions
(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn solution1 []
  (->> (getSteps)
       (reduce runStep startingStacks)
       (map first)
       (reduce str)))

(solution1)


(ns advent-of-code.day7
  (:require [clojure.string :as str]))

(defprotocol FileSystemNode
  (total-size)
  (children)
  (parent))

(deftype File [parent size]
  FileSystemNode
  (total-size [] size)
  (children [] [])
  (parent [] parent))

(deftype Folder [p]

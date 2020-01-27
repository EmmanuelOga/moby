(ns moby.util
  (:require [clojure.java.io :as io])
  (:import (javax.xml.transform.stream StreamSource)))

(defn xml-stream [xml-path]
  (-> xml-path io/resource io/reader StreamSource.))

(defn bounded-length-string [str len]
  "Return a string of len or less characters"
  (cond
    (< len 1) ""
    (< (count val) len) val
    :else (subs val 0 (- len 1))))

(defn shorten-string [val len]
  "return a string of len size or trim it to len plus a postfix: ..."
  (cond
    (< (count val) len) val
    :else (str (bounded-length-string val len) "...")))

(defn short
  "convert param to string, but trim to max if string is very long"
  ([obj] (short obj 255))
  ([obj max] (short-string (str obj) max)))

(ns moby.util
  (:require [clojure.java.io :as io])
  (:import (javax.xml.transform.stream StreamSource)))

(defn xml-stream [xml-path]
  (-> xml-path io/resource io/reader StreamSource.))


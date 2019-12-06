(ns moby.xslt
  (:require [clojure.java.io :as io])
  (:use moby.util)
  (:import (net.sf.saxon.s9api Processor Serializer$Property)
           (java.io StringWriter)
           (javax.xml.transform.stream StreamSource)))

(def saxon
  "The default XSLT processor and compiler (Saxon HE edition)"
  (let [p (Processor. false) c (.newXsltCompiler default-processor)]
    {:processor p :compiler c}))

(defn compile-stylesheet
  "Return a Saxon stylesheet, 3.0+ API"
  [xsl-path {:keys [compiler] :as saxon}]
  (let [stylesheet (.compile compiler (xml-stream xsl-path))] (.load30 stylesheet)))

(defn xml-serializer
  [{:keys [processor] :as saxon}]
  (let [serializer (.newSerializer processor) string-writer (StringWriter.)]
    (doto serializer
      (.setOutputProperty Serializer$Property/METHOD, "xml")
      (.setOutputWriter string-writer))
    {:serializer serializer :buffer string-writer}))

; TransformA example.
; Compile and execute a simple transformation that applies a stylesheet to an input file
; and serializing the result to an output file)
(defn xslt-transform
  ([xsl-path xml-path] (xslt-transform xsl-path xml-path saxon))
  ([xsl-path xml-path {:keys [processor compiler] :as saxon}]
   (let [stylesheet (compile-stylesheet xsl-path saxon)
         {:keys [serializer buffer]} (xml-serializer saxon)]
     (.transform stylesheet (xml-stream xml-path) serializer)
     buffer)))

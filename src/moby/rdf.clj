(ns moby.rdf
  (:require [clojure.java.io :as io])
  (:import (org.eclipse.rdf4j.rio Rio RDFFormat)
           (org.eclipse.rdf4j.model Resource Statement)
           (org.eclipse.rdf4j.model.impl SimpleValueFactory)
           (java.io Writer)))

(def uri-base "https://emmanueloga.com/")
(def uri-rdfpub "https://eoga.dev/rdfpub/")
(def uri-sdoc "https://eoga.dev/sdoc/")

(def empty-context (make-array Resource 0))

(defn parse-rdf
  ([path] (parse-rdf path RDFFormat/TURTLE))
  ([path format]
   (let [stream (io/input-stream (io/resource path))]
     (Rio/parse stream uri-base format empty-context))))

(defn iri [prefix postfix]
  (. (SimpleValueFactory/getInstance) createIRI prefix postfix))

(def rp:map (iri uri-rdfpub "map"))
(def rp:topic (iri uri-rdfpub "topic"))
(def rp:xml (iri uri-rdfpub "xml"))

(defn sel [model & {:keys [s p o]}]
  (->
    (. model filter s p o empty-context)
    .iterator
    iterator-seq))

(defmethod print-method Statement [v ^Writer w]
  (let [s (short (. v getSubject))
        p (short (. v getPredicate))
        o (short (. v getObject))
        c (short (. v getContext))]
    (.write w (str {:s s :p p :o o :c c}))))

(def model (parse-rdf "document.ttl"))

(sel model :o rp:map)
(sel model :o rp:topic)
(sel model :p rp:xml)

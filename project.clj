(defproject moby "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.eclipse.rdf4j/rdf4j-runtime "3.0.2" :extension "pom"]
                 [org.eclipse.rdf4j/rdf4j-queryresultio-text "3.0.2"]
                 [org.slf4j/slf4j-simple "1.7.29"]
                 [net.sf.saxon/Saxon-HE "9.9.1-5"]]
  :repl-options {:init-ns moby.core})

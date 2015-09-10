(defproject beta "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :resource-paths ["resources"]

  ;; Adds some dependencies
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [lib-noir "0.9.9"]                         ;; utilites
                 [ring "1.4.0"]                             ;; http abstractions
                 [ring/ring-defaults "0.1.5"]               ;; http default settings
                 [http-kit "2.1.18"]                        ;; http server
                 [selmer "0.9.1"]])                         ;; templating

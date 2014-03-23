(defproject garden "1.1.6-SNAPSHOT"
  :description "Generate CSS from Clojure data structures."
  :url "https://github.com/noprompt/garden"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj" "target/generated-src/clj" "target/generated-src/cljs"]
  :test-paths ["test" "target/generated-test"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [com.yahoo.platform.yui/yuicompressor "2.4.7"]]
  :plugins [[codox "0.6.4"]
            [lein-cljsbuild "1.0.2"]
            [com.keminglabs/cljx "0.3.1"]
            [com.cemerick/clojurescript.test "0.2.1"]]
  :profiles {:dev {:dependencies [[criterium "0.4.1"]
                                  [com.cemerick/piggieback "0.1.2"]]
                   :plugins [[com.cemerick/austin "0.1.3"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}
  :aliases {"test-all" ["do" "clean," "cljx" "once," "test," "cljsbuild" "test"]}
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/generated-src/clj"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated-src/cljs"
                   :rules :cljs}
                  {:source-paths ["test"]
                   :output-path "target/generated-test"
                   :rules :clj}
                  {:source-paths ["test"]
                   :output-path "target/generated-test"
                   :rules :cljs}]}
  :cljsbuild {:builds [{:source-paths ["target/generated-src/cljs" "target/generated-test"]
                        :compiler {:output-to "target/cljs/testable.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]
              :test-commands {"unit-tests" ["phantomjs" :runner "target/cljs/testable.js"]}})

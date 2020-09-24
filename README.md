# native-cogaws

A sketch of the steps necessary to get the cognitect aws s3 api read and write from inside a native-image executable. This repo was built with GraalVM 20.2.0 and the below steps may change in future versions.

The steps are roughly:
1. generate reflection, resource, proxy, and jni configuration files by building an uberjar that calls all api functions you expect to call. Then run the uberjar with the following command:

 ```$GRAALVM_HOME/bin/java -agentlib:native-image-agent=config-output-dir=/path/to/config-dir/ <your uberjar run command>```

The above uses graalvm's "agent" to track usages of java's dynamic features and generate the appropriate config files. See [here](https://www.graalvm.org/reference-manual/native-image/Configuration/#assisted-configuration-of-native-image-builds) for more details.

2. point native-image to the config files directory by giving it the following flag `-H:ConfigurationFileDirectories=<path/to/config/directory>`
3. explicitly require namespaces that throw errors related to `defineClass` in your clojure code (this requires running your native-image executables and seeeing if they throw this exception for any namespaces)
4. enable native-image support for https by adding `-H:EnableURLProtocols=http,https` to the native-image compile flags. [reference](https://www.graalvm.org/reference-manual/native-image/URLProtocols/)
5. add borkdude/clj-reflector-graal-java11-fix to fix UnsupportedFeatureError in clojure.lang.Reflector related to MethodHandle. [reference](https://github.com/borkdude/clj-reflector-graal-java11-fix#the-problem)

## License

Copyright © 2020 User

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

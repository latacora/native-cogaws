# native-cogaws

A sketch of the steps necessary to get the cognitect aws s3 api read and write from inside a native-image executable.

The steps are roughly:
1. generate reflection, resource, proxy, etc. configuration files by running an uberjar that calls your functions
2. explicitly require namespaces that throw errors related to `defineClass` in your clojure code (this requires running your native-image executables and seeeing if they throw this exception for any namespaces)
3. enable native-image support for https
4. add borkdude/clj-reflector-graal-java11-fix to fix  UnsupportedFeatureError for MethodHandle

## License

Copyright © 2020 User

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

etc/security
============

This contains configuration related to the security stage of the build.

Surpressed dependency checks:

 * com.apple:AppleJavaExtensions 
   This is inherited by FindBugs as part of the build script but doesn't 
   impact the project.
 * com.google.protobuf:protobuf-java  
   We don't actually expose this anywhere, but it is a transitive of a number
   of things. This has an open CVE for int overflows generally on all data 
   serialization libs.
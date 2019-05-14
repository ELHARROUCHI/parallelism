compiling app:
    nix system: ./gradleW fatJar
    windows: gradlew fatJar
    
run app
    java -jar build/libd/test-0.1.jar PATH_FILE_TO_COPY
    
min version java: 11

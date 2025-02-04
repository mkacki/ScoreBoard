clean:
	mvn clean
test:
	mvn test
clean_package:
	mvn clean package
docker_prepare_builder:
	docker build --build-arg MAVEN_GOAL="clean package" -t maven-builder .
docker_clean_package:
	docker run -e MAVEN_GOAL="clean package" -v ./:/workspace -v ~/.m2:/root/.m2 maven-builder


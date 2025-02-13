
# Scoreboard

Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.

**The scoreboard supports the following operations:**
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   - a) Home team
   - b) Away team
   
2. Update score. This should receive a pair of absolute scores: home team score and away team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the 
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

## Other requirements derived from game rules
 - Each team in given time, can play only one match.
 - When Match is finished both teams can play in other Matches.
 - Score update operation cannot lower team score.
 - Home and Away team must be two different teams
 - Home and Away teams must be provided - not null, not blank

## Technical requirements
- Thread safe Scoreboard modification

## Build process

Application created using java 17 and maven 3
You can build also using docker

- Using maven
   ```
   mvn clean package
   ```
- Using docker
   ```
  docker build --build-arg MAVEN_GOAL="clean package" -t maven-builder .
  docker run -e MAVEN_GOAL="clean package" -v ./:/workspace -v ~/.m2:/root/.m2 maven-builder
   ```  
- Using make
  - With maven
    ```
        make clean_package
    ```
  - Alternatively using docker
    ```
      make docker_prepare_builder
      make docker_clean_package
    ```  

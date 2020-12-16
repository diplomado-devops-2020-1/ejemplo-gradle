
/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
  
 stage('build & test')
                    {
                    	bat 'gradle clean build'
                    }
                    stage('sonar')
                    {
                    	def scannerHome = tool 'sonar-scanner';
    					withSonarQubeEnv('sonar') { 
      					
                         bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"

                        }
                    }
                    stage('run')
                    {
                        bat "nohup start gradlew bootRun &"
                        sleep 20

                    }
                    stage('test')
                    {
                        bat "curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing"
                        sleep 20
 
                    }
                    stage('nexus')
                    {
                        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'C:\\Users\\passy\\Documents\\DiploDevOps\\ejemplo-maven\\build\\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]

                    }

}

return this;

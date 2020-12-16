/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
  
  
    stage('Compile') {
             
        
                bat './mvnw.cmd clean compile -e'
        
            
           
        }

            stage('Unit') {
             
          
        
                bat './mvnw.cmd clean test -e'
           
      }

            stage('Jar') {
             
          
 
                bat './mvnw.cmd clean package -e'
 
          
        }

             stage('Run') {
             
 
 
                echo 'nohup bash mvnw spring-boot:run &'
 

          
        }

        stage('Test') {
           
 
 
               bat 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'

       }
  

}

return this;

pipeline{
agent any
  parameters { choice(name: 'buildtool', choices: ['gradle','maven'], description: 'Elección de herramienta de construcción para aplicación covid')}
  
  stages{
    stage('Hello')
    {
      steps
      {
        script
        {
          println 'Herramienta de ejecución seleccionada: ' + params.buildtool
          def pipe = load "${params.buildtool}.groovy"
          pipe.call()
        }
      }
    }
  }
  post{
	success{
slackSend channel: 'U01D689C92A', color: 'good', message: 'Ejecución exitosaa', teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'f7b6dd93-5a8e-4493-9428-37004efbe394'
	}
  
  }
}

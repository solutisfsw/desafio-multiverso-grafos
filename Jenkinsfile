node {
    stage('SCM Checkout'){        
        git 'https://github.com/Adailton90/CrudPessoa'
    }
    stage('build'){
        //Get maven home path
        def mvnHome = tool name: 'maven', type: 'maven'
        sh "${mvnHome}/bin/mvn package"  
    }
    
}

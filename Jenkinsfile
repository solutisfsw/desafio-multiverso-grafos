node {
    stage('SCM Checkout'){        
        git 'https://github.com/Adailton90/desafio-multiverso-grafos'
    }
    script {
        System.setProperty("org.jenkinsci.plugins.durabletask.BourneShellScript.HEARTBEAT_CHECK_INTERVAL", "86400");
     }
    stage('build'){
        //Get maven home path
        sh 'cd $WORKSPACE/'
        def mvnHome = tool name: 'maven', type: 'maven'
        sh "${mvnHome}/bin/mvn package"  
    }
    
}

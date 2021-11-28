CS1660 Course Project Option 1

Steps:
1. Create a new GKE cluster with default settings
2. Once the cluster is created, connect the Cloud Shell Terminal to the cluster. This was done through the GCP website with the connect button on the cluster page. Otherwise this can be done with "gcloud container clusters get-credentials <cluster-name> --zone <project zone name> --project <project id>"
3. Inside the cluster, use "git clone https://github.com/latte164/CS1660TermProjectYamls" to download the yaml files then "cd CS1660TermProjectYamls"
4. Now, to create the deployments and services, run "kubectl apply -f ." Wait for the deployments under the workloads tab to all have status "OK" (except for the hadoop-deployment as this does not currently work). You may need to refresh the page to see any status updates for the deployments.
5. Next, build the docker container for the driver by using "docker build -t latte164/term-project-driver ." in the same directory as the Dockerfile, driver.py, and driver.html Note: When creating a new cluster, deployments, and services, the driver.py must be updated so the hyperlinks on line 7 correspond with the newly created ones.
6. Open "http://localhost:7050/" in your browser for the GUI and navigate around. You may need "2570c528a2da138d709a9c39135beb7897d8ed0f7d5931ae" as the token for Jupyter Notebook and username: "admin"; password: "admin" for Sonar.

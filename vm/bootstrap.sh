apt-get update

# instalação do docker
sudo apt-get install docker.io -y
sudo groupadd docker
sudo usermod -aG docker vagrant
newgrp docker

# instalação docker compose
sudo curl -4L "https://github.com/docker/compose/releases/download/1.28.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# instala apache bench
sudo apt-get install -y apache2-utils

# instalação do java 11
curl -4O https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
tar zxvf openjdk-11.0.2_linux-x64_bin.tar.gz
sudo mv jdk-11* /usr/local/
sudo echo "JAVA_HOME=/usr/local/jdk-11.0.2" >> /etc/profile.d/jdk.sh
sudo echo "PATH=\$PATH:\$JAVA_HOME/bin" >> /etc/profile.d/jdk.sh
sudo echo "export JAVA_HOME" >> /etc/profile.d/jdk.sh
rm openjdk-11.0.2_linux-x64_bin.tar.gz

# modifica .bashrc
cat /home/vagrant/vm_conf_files/bashrc >> /home/vagrant/.bashrc

# instala bash completion
sudo apt-get install bash-completion

# instala maven
sudo apt-get -y install maven

sudo apt-get -y install gradle

# clone project 
git clone -q -b master git@github.com:FelipeFMMobile/tradewallet_ws.git

# instala minikube
#curl -4LO https://storage.googleapis.com/minikube/releases/latest/minikube_latest_amd64.deb
#sudo dpkg -i minikube_latest_amd64.deb

# instala o kubectl
#curl -4LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
#sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl


# kubectl autocompletion
#echo 'source <(kubectl completion bash)' >>~/.bashrc
#kubectl completion bash >/etc/bash_completion.d/kubectl

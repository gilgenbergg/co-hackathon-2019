# co-hackathon-2019

Installing dependencies (VM has those installed)
1. Install Java Open JDK (NOT JRE) 8 https://openjdk.java.net/install/index.html
2. Install Node.js LTS https://nodejs.org/en/download/

Cloning the repository YourWorkspace = workspace on VM
cd ~/<YourWorkspace>
git clone https://github.com/DodoTheDeveloper/co-hackathon-2019.git

Getting the backend running
cd ~/<YourWorkspace>/co-hackathon-2019/co-backend 
mvn clean install
mvn tomee:run

Getting the backend running
cd ~/<YourWorkspace>/co-hackathon-2019/co-frontend
npm install
ng serve
  
Backend should now be runnning on http://localhost:8080 and the frontend on http://localhost:4200

Please check your Studo & Telegram for additional information during the hackathon.

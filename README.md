# co-hackathon-2019

## Installing dependencies (VM has those installed)
1. Install Java Open JDK (NOT JRE) 8 https://openjdk.java.net/install/index.html
2. Install Maven 3.6.x https://maven.apache.org/download.cgi
3. Install Node.js LTS https://nodejs.org/en/download/
4. Install NPM (should come with Node.js on Windows) https://www.npmjs.com/get-npm
5. Install Angular https://cli.angular.io/

Optional:
Recommended IDE https://code.visualstudio.com/
Recommended REST Client: https://support.insomnia.rest/

```
// Cloning the repository <YourWorkspace> = workspace on VM
cd ~/<YourWorkspace>
git clone https://github.com/DodoTheDeveloper/co-hackathon-2019.git

// Getting the backend running
cd ~/<YourWorkspace>/co-hackathon-2019/co-backend 
mvn clean install // invoke every time you make changes to the source code
mvn tomee:run // starts a tomEE server on http://localhost:8080

// Getting the backend running
cd ~/<YourWorkspace>/co-hackathon-2019/co-frontend
npm install // installs all node modules
npm start // use this not ng serve or else you will have issues with the backend (proxy config)
```
Backend should now be runnning on http://localhost:8080 and the frontend on http://localhost:4200

Please check this page for additional information during the hackathon.

package de.stefan_oltmann.oni

val indexHtml = """
    
    <html>

    <body>

    <script>

    function fetchFile() {
    
      let fileInput = document.getElementById("fileInput");

      let reader = new FileReader();

      reader.onload = function () {

        let fileContent = new Blob([reader.result]);

        fetch("http://localhost:8080/upload", {
          method: "post",
          body: fileContent
        })
          .then(res => res.text())
          .then(txt => {
           let resultElement = document.createElement("p");
           resultElement.textContent = txt;
           document.body.appendChild(resultElement);
          })
          .catch(err => console.log(err));
      };

     reader.readAsArrayBuffer(fileInput.files[0]);
    }
    </script>

    <input type="file" id="fileInput">
    <input type="button" value="POST File" onclick="fetchFile()">

    </body>
    </html>
    
""".trimIndent()
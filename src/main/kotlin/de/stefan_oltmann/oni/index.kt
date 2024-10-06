/*
 * ONI Seed Browser backend
 * Copyright (C) 2024 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import os
from flask import Flask, render_template, request, send_file
import glob 

__author__ = 'ze'

app = Flask(__name__)

APP_ROOT = os.path.dirname(os.path.abspath(__file__))

@app.route("/")
def index():
    return render_template("upload.html")

@app.route("/upload", methods=['POST'])

#ENVIAR IMAGENS PARA PASTA

def upload():
    target = os.path.join(APP_ROOT, 'images/')
    print(target)
    cpf=request.form.getlist("cpf")
    cpf=cpf[0]

    if not os.path.isdir(target):
        os.mkdir(target)
    if not os.path.isdir(target+cpf):
    	print(target+cpf)
        os.mkdir(target+cpf)
    cont=0
    for file in request.files.getlist("file"):
        filename = file.filename
        filename =  filename.split(".")
        filename[0] = str(cont) 
        filename=filename[0]+"."+filename[1]
        print(filename)
        destination = "/".join([target+cpf, filename])
        print(destination)
        file.save(destination)
        cont+=1


    return render_template("complete.html")

#OBTER IMAGENS PARA LINK
@app.route("/imagem")
def image():
	if 'img' in request.args:
		pos=request.args["img"]
		return send_file(pos)
if __name__ == "__main__":
    app.run(host='192.168.1.103', port=8081, debug = True)
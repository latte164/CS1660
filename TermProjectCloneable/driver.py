from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def landing_page():
	return "<html><div><h1>Click a link below to navigate to the service:</h1></div><div><p><a href=\"http://localhost:8000\">Jupyter Notebook</a></p><p><a href=\"http://localhost:8080\">Apache Spark</a></p><p><a href=\"http://localhost:9000\">Sonar</a></p><p><a href=\"http://localhost:9050\">Hadoop</a></p></div><div><p>Made by William Thomas</p></div></html>"

if __name__ == '__main__':
	app.run(host='0.0.0.0', port=7050)
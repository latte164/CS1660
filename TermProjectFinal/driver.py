from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def landing_page():
	return "<html><div><h1>Click a link below to navigate to the service:</h1></div><div><p><a href=\"http://35.238.213.93/\">Jupyter Notebook</a></p><p><a href=\"http://34.132.5.252/\">Apache Spark</a></p><p><a href=\"http://35.188.55.216/sessions/new?return_to=%2F\">Sonar</a></p><p><a href=\"http://34.69.142.200/\">Hadoop</a></p></div><div><p>Made by William Thomas</p></div></html>"

if __name__ == '__main__':
	app.run(host='0.0.0.0', port=7050)
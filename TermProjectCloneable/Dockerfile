FROM python:3
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 7050
RUN pip install Flask
ENTRYPOINT ["python"]
CMD ["driver.py"]
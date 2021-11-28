FROM python:3
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN pip install Flask
EXPOSE 7050
ENTRYPOINT ["python"]
CMD ["driver.py"]
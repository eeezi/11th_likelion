from django.db import models

class Todo(models.Model):
    title = models.CharField(max_length=100)
    description = models.TextField(blank= True)
    complete = models.BooleanField(default=False)
    important = models.BooleanField(default=False)
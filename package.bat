@echo on
@echo =============================================================
@echo $                                                           $
@echo $               ludan Microservices-Platform                  $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  ludan All Right Reserved                                   $
@echo $  Copyright (C) 2019-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title ludan Microservices-Platform
@color 0e

call mvn clean package -Dmaven.test.skip=true

pause
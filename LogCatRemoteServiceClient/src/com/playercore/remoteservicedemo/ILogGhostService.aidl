package com.playercore.remoteservicedemo;

interface ILogGhostService {
	boolean isStarted();
	void start();
	void stop();
	void clean();
}

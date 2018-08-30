package com.yuansong.common;

public class IdWorker {
	
	//开始时间截
	private final static long twepoch = 1288834974657L;
	//worker位数长度
	private final static long workerIdBits = 5L;
	//data center位数长度
	private final static long datacenterIdBits = 5L;
	//最大workerId
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	//最大datacenterId
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	//顺序号位数长度
	private final static long sequenceBits = 12L;
	//workerId左移位数
	private final static long workerIdShift = sequenceBits;
	//data center左移位数
	private final static long datacenterIdShift = sequenceBits + workerIdBits;
	//时间戳左移位数
	private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	//生成序列的掩码
	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);
	//上一次生成ID的时间戳
	private static long lastTimestamp = -1L;
	
	//毫秒内序列
	private long sequence = 0L;
	
	private final long workerId;
	
	private final long datacenterId;
	
	public IdWorker() {
		this.workerId = 0L;
		this.datacenterId = 0L;
	}
	
	public IdWorker(long workId, long datacenterId) {
		if(workId > maxWorkerId || workId < 0) {
			throw new IllegalArgumentException("WorkerId can't be greater than " + maxWorkerId + " or less than 0.");
		}
		if(datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException("WorkerId can't be greater than " + maxDatacenterId + " or less than 0.");
		}
		this.workerId = workId;
		this.datacenterId = datacenterId;
	}
	
	public synchronized long nextId() {
		
		long timestamp = timeGen();
		
		//如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if(timestamp < lastTimestamp) {
			throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		
		if(lastTimestamp == timestamp) {
			//同一时间戳内
			sequence = (sequence + 1) & sequenceMask;
			if(sequence == 0) {
				//毫秒内序列溢出，阻塞到下一毫秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		}
		else {
			sequence = 0L;
		}
		
		lastTimestamp = timestamp;
		
		// ID偏移组合生成最终的ID，并返回ID 
		long nextId = ((timestamp - twepoch) << timestampLeftShift) 
			| (datacenterId << datacenterIdShift) 
			| (workerId << workerIdShift) | sequence; 
		
		return nextId; 
	}
	
	private long timeGen() {
		return System.currentTimeMillis();
	}
	
	private long tilNextMillis(final long lastTimestamp) { 
		long timestamp = this.timeGen(); 
		while (timestamp <= lastTimestamp) { 
			timestamp = this.timeGen(); 
		} 
		return timestamp; 
	}

}

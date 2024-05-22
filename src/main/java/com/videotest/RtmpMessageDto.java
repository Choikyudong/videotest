package com.videotest;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class RtmpMessageDto {

	private final int header;
	private final int messageType;
	private final ByteBuf payload;

	public RtmpMessageDto(int header, int messageType, ByteBuf payload) {
		this.header = header;
		this.messageType = messageType;
		this.payload = payload;
	}

}

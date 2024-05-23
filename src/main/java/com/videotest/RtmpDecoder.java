package com.videotest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RtmpDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// RTMP 메시지 파싱 로직을 여기에 구현
		// 예를 들어, 메시지 헤더를 읽고 메시지 본문을 읽어들이는 작업
		if (in.readableBytes() < 12) {
			return; // 메시지가 완전하지 않으면 기다린다
		}

		// 헤더를 읽는다 (예시)
		int header = in.readInt();
		int payloadLength = in.readInt();
		int messageType = in.readInt();

		if (in.readableBytes() < payloadLength) {
			return; // 메시지 본문이 완전하지 않으면 기다린다
		}

		ByteBuf payload = in.readBytes(payloadLength);
		RtmpMessageDto message = new RtmpMessageDto(header, messageType, payload);
		out.add(message);
	}
}

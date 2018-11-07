// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: account.proto at 14:1
package com.tmauto.argus.mapi.model;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class TMRespStatus extends Message<TMRespStatus, TMRespStatus.Builder> {
  public static final ProtoAdapter<TMRespStatus> ADAPTER = new ProtoAdapter_TMRespStatus();

  private static final long serialVersionUID = 0L;

  public static final Integer DEFAULT_CODE = 0;

  public static final String DEFAULT_MSG = "";

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer code;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String msg;

  public TMRespStatus(Integer code, String msg) {
    this(code, msg, ByteString.EMPTY);
  }

  public TMRespStatus(Integer code, String msg, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.code = code;
    this.msg = msg;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.code = code;
    builder.msg = msg;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof TMRespStatus)) return false;
    TMRespStatus o = (TMRespStatus) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(code, o.code)
        && Internal.equals(msg, o.msg);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (code != null ? code.hashCode() : 0);
      result = result * 37 + (msg != null ? msg.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (code != null) builder.append(", code=").append(code);
    if (msg != null) builder.append(", msg=").append(msg);
    return builder.replace(0, 2, "TMRespStatus{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<TMRespStatus, Builder> {
    public Integer code;

    public String msg;

    public Builder() {
    }

    public Builder code(Integer code) {
      this.code = code;
      return this;
    }

    public Builder msg(String msg) {
      this.msg = msg;
      return this;
    }

    @Override
    public TMRespStatus build() {
      return new TMRespStatus(code, msg, super.buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_TMRespStatus extends ProtoAdapter<TMRespStatus> {
    ProtoAdapter_TMRespStatus() {
      super(FieldEncoding.LENGTH_DELIMITED, TMRespStatus.class);
    }

    @Override
    public int encodedSize(TMRespStatus value) {
      return (value.code != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, value.code) : 0)
          + (value.msg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value.msg) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, TMRespStatus value) throws IOException {
      if (value.code != null) ProtoAdapter.INT32.encodeWithTag(writer, 1, value.code);
      if (value.msg != null) ProtoAdapter.STRING.encodeWithTag(writer, 2, value.msg);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public TMRespStatus decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.code(ProtoAdapter.INT32.decode(reader)); break;
          case 2: builder.msg(ProtoAdapter.STRING.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public TMRespStatus redact(TMRespStatus value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
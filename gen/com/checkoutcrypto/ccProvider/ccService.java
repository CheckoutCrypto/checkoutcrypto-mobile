/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/skynet/DATA/WORK/tuna_workspace/tuna_practise/checkoutcrypto/src/com/checkoutcrypto/ccProvider/ccService.aidl
 */
package com.checkoutcrypto.ccProvider;
// Declare any non-default types here with import statements
/** Example service interface */
public interface ccService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.checkoutcrypto.ccProvider.ccService
{
private static final java.lang.String DESCRIPTOR = "com.checkoutcrypto.ccProvider.ccService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.checkoutcrypto.ccProvider.ccService interface,
 * generating a proxy if needed.
 */
public static com.checkoutcrypto.ccProvider.ccService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.checkoutcrypto.ccProvider.ccService))) {
return ((com.checkoutcrypto.ccProvider.ccService)iin);
}
return new com.checkoutcrypto.ccProvider.ccService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getKey();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getAppName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getAppName();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
boolean _arg4;
_arg4 = (0!=data.readInt());
this.setKey(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.checkoutcrypto.ccProvider.ccService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/** Request the process ID of this service, to do evil things with it. */
@Override public java.lang.String getKey() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getKey, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getAppName() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAppName, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setKey(java.lang.String uid, java.lang.String appname, java.lang.String apikey, java.lang.String updated, boolean update) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(uid);
_data.writeString(appname);
_data.writeString(apikey);
_data.writeString(updated);
_data.writeInt(((update)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setKey, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getAppName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
/** Request the process ID of this service, to do evil things with it. */
public java.lang.String getKey() throws android.os.RemoteException;
public java.lang.String getAppName() throws android.os.RemoteException;
public void setKey(java.lang.String uid, java.lang.String appname, java.lang.String apikey, java.lang.String updated, boolean update) throws android.os.RemoteException;
}

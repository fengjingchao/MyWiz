package com.example.demo2;

public class Xlog {
    private static final boolean DEBUG = true;
    private static final String TAG = "Xlog";

    private static boolean isLoggable(int level) {
        if (DEBUG) {
            return true;
        } else {
            return false;
        }
    }

    public static void v(String tag, String format, Object... args) {
        v(tag, format, null, args);
    }


    public static void v(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.VERBOSE)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.v(tag, format);
                } else {
                    android.util.Log.v(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.v(tag, format, tr);
                } else {
                    android.util.Log.v(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void d(String tag, String format, Object... args) {
        d(tag, format, null, args);
    }


    public static void d(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.DEBUG)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.d(tag, format);
                } else {
                    android.util.Log.d(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.d(tag, format, tr);
                } else {
                    android.util.Log.d(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void i(String tag, String format, Object... args) {
        i(tag, format, null, args);
    }


    public static void i(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.INFO)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.i(tag, format);
                } else {
                    android.util.Log.i(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.i(tag, format, tr);
                } else {
                    android.util.Log.i(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, String format, Object... args) {
        w(tag, format, null, args);
    }


    public static void w(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.WARN)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.w(tag, format);
                } else {
                    android.util.Log.w(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.w(tag, format, tr);
                } else {
                    android.util.Log.w(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, Throwable tr) {
        w(tag, "Xlog.warn", tr);
    }

    public static void e(String tag, String format, Object... args) {
        e(tag, format, null, args);
    }


    public static void e(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.ERROR)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.e(tag, format);
                } else {
                    android.util.Log.e(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.e(tag, format, tr);
                } else {
                    android.util.Log.e(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wtf(String tag, String format, Object... args) {
        wtf(tag, format, null, args);
    }

    public static void wtf(String tag, Throwable tr) {
        wtf(tag, "wtf", tr);
    }


    public static void wtf(String tag, String format, Throwable tr, Object... args) {
        try {
            if (!isLoggable(android.util.Log.DEBUG)) {
                return;
            }

            if (tr == null) {
                if (args.length==0) {
                    android.util.Log.wtf(tag, format);
                } else {
                    android.util.Log.wtf(tag, String.format(format, args));
                }

            } else {
                if (args.length==0) {
                    android.util.Log.wtf(tag, format, tr);
                } else {
                    android.util.Log.wtf(tag, String.format(format, args), tr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

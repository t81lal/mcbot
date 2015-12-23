package org.topdank.mc.bot.util;

import java.util.Random;
import java.util.UUID;

public final class MathHelper {

	/**
	 * A table of sin values computed from 0 (inclusive) to 2*pi (exclusive),
	 * with steps of 2*PI / 65536.
	 */
	private static final float[] SIN_TABLE = new float[65536];

	/**
	 * Though it looks like an array, this is really more like a mapping. Key
	 * (index of this array) is the upper 5 bits of the result of multiplying a
	 * 32-bit unsigned integer by the B(2, 5) De Bruijn sequence 0x077CB531.
	 * Value (value stored in the array) is the unique index (from the right) of
	 * the leftmost one-bit in a 32-bit unsigned integer that can cause the
	 * upper 5 bits to get that value. Used for highly optimized "find the
	 * log-base-2 of this number" calculations.
	 */
	private static final int[] multiplyDeBruijnBitPosition;

	static {
		for (int i = 0; i < 65536; ++i) {
			SIN_TABLE[i] = (float) Math.sin(i * Math.PI * 2.0D / 65536.0D);
		}

		multiplyDeBruijnBitPosition = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
	}

	public static final int BLOCK_X_POS_MAX_LONG_DECODE_BITS = 1 + calculateLogBaseTwo(roundUpToPowerOfTwo(30000000));
	public static final int BLOCK_Z_POS_MAX_LONG_DECODE_BITS = BLOCK_X_POS_MAX_LONG_DECODE_BITS;
	public static final int BLOCK_Y_POS_MAX_LONG__DECODE_BITS = 64 - BLOCK_X_POS_MAX_LONG_DECODE_BITS - BLOCK_Z_POS_MAX_LONG_DECODE_BITS;
	public static final int BLOCK_Y_POS_MAX_LONG__DECODE_BITS_COPY = 0 + BLOCK_Z_POS_MAX_LONG_DECODE_BITS;
	public static final int BLOCK_XY_ZY_POS_MAX_LONG_DECODE_BITS = BLOCK_Y_POS_MAX_LONG__DECODE_BITS_COPY + BLOCK_Y_POS_MAX_LONG__DECODE_BITS;

	public static final long BLOCK_X_POS_MAX_LONG_ENCODE_BITS = (1L << BLOCK_X_POS_MAX_LONG_DECODE_BITS) - 1L;
	public static final long BLOCK_Y_POS_MAX_LONG_ENCODE_BITS = (1L << BLOCK_Y_POS_MAX_LONG__DECODE_BITS) - 1L;
	public static final long BLOCK_Z_POS_MAX_LONG_ENCODE_BITS = (1L << BLOCK_Z_POS_MAX_LONG_DECODE_BITS) - 1L;

	public static final float SQRT_TWO = sqrt_float(2.0F);

	/**
	 * sin looked up in a table
	 */
	public static float sin(float angle) {
		return SIN_TABLE[(int) (angle * 10430.378F) & 65535];
	}

	/**
	 * cos looked up in the sin table with the appropriate offset
	 */
	public static float cos(float p_76134_0_) {
		return SIN_TABLE[(int) (p_76134_0_ * 10430.378F + 16384.0F) & 65535];
	}

	public static float sqrt_float(float f) {
		return (float) Math.sqrt(f);
	}

	public static float sqrt_double(double d) {
		return (float) Math.sqrt(d);
	}

	/**
	 * Returns the greatest integer less than or equal to the float argument
	 */
	public static int floor_float(float f) {
		int intF = (int) f;
		return f < intF ? intF - 1 : intF;
	}

	/**
	 * returns cast as an int, and no greater than Integer.MAX_VALUE-1024
	 */
	public static int truncateDoubleToInt(double d) {
		return (int) (d + 1024.0D) - 1024;
	}

	/**
	 * Returns the greatest integer less than or equal to the double argument
	 */
	public static int floor_double(double d) {
		int intD = (int) d;
		return d < intD ? intD - 1 : intD;
	}

	/**
	 * Long version of floor_double
	 */
	public static long floor_double_long(double d) {
		long longD = (long) d;
		return d < longD ? longD - 1L : longD;
	}

	public static int func_154353_e(double p_154353_0_) {
		return (int) (p_154353_0_ >= 0.0D ? p_154353_0_ : -p_154353_0_ + 1.0D);
	}

	public static float abs(float f) {
		return f >= 0.0F ? f : -f;
	}

	/**
	 * Returns the unsigned value of an int.
	 */
	public static int abs_int(int p_76130_0_) {
		return p_76130_0_ >= 0 ? p_76130_0_ : -p_76130_0_;
	}

	public static int ceiling_float_int(float p_76123_0_) {
		int var1 = (int) p_76123_0_;
		return p_76123_0_ > var1 ? var1 + 1 : var1;
	}

	public static int ceiling_double_int(double p_76143_0_) {
		int var2 = (int) p_76143_0_;
		return p_76143_0_ > var2 ? var2 + 1 : var2;
	}

	/**
	 * Returns the value of the first parameter, clamped to be within the lower
	 * and upper limits given by the second and third parameters.
	 */
	public static int clamp_int(int p_76125_0_, int p_76125_1_, int p_76125_2_) {
		return p_76125_0_ < p_76125_1_ ? p_76125_1_ : (p_76125_0_ > p_76125_2_ ? p_76125_2_ : p_76125_0_);
	}

	/**
	 * Returns the value of the first parameter, clamped to be within the lower
	 * and upper limits given by the second and third parameters
	 */
	public static float clamp_float(float p_76131_0_, float p_76131_1_, float p_76131_2_) {
		return p_76131_0_ < p_76131_1_ ? p_76131_1_ : (p_76131_0_ > p_76131_2_ ? p_76131_2_ : p_76131_0_);
	}

	public static double clamp_double(double p_151237_0_, double p_151237_2_, double p_151237_4_) {
		return p_151237_0_ < p_151237_2_ ? p_151237_2_ : (p_151237_0_ > p_151237_4_ ? p_151237_4_ : p_151237_0_);
	}

	public static double denormalizeClamp(double p_151238_0_, double p_151238_2_, double p_151238_4_) {
		return p_151238_4_ < 0.0D ? p_151238_0_ : (p_151238_4_ > 1.0D ? p_151238_2_ : p_151238_0_ + (p_151238_2_ - p_151238_0_) * p_151238_4_);
	}

	/**
	 * Maximum of the absolute value of two numbers.
	 */
	public static double abs_max(double p_76132_0_, double p_76132_2_) {
		if (p_76132_0_ < 0.0D) {
			p_76132_0_ = -p_76132_0_;
		}

		if (p_76132_2_ < 0.0D) {
			p_76132_2_ = -p_76132_2_;
		}

		return p_76132_0_ > p_76132_2_ ? p_76132_0_ : p_76132_2_;
	}

	/**
	 * Buckets an integer with specifed bucket sizes. Args: i, bucketSize
	 */
	public static int bucketInt(int p_76137_0_, int p_76137_1_) {
		return p_76137_0_ < 0 ? -((-p_76137_0_ - 1) / p_76137_1_) - 1 : p_76137_0_ / p_76137_1_;
	}

	public static int getRandomIntegerInRange(Random p_76136_0_, int p_76136_1_, int p_76136_2_) {
		return p_76136_1_ >= p_76136_2_ ? p_76136_1_ : p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + 1) + p_76136_1_;
	}

	public static float randomFloatClamp(Random p_151240_0_, float p_151240_1_, float p_151240_2_) {
		return p_151240_1_ >= p_151240_2_ ? p_151240_1_ : p_151240_0_.nextFloat() * (p_151240_2_ - p_151240_1_) + p_151240_1_;
	}

	public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_) {
		return p_82716_1_ >= p_82716_3_ ? p_82716_1_ : p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_;
	}

	public static double average(long[] p_76127_0_) {
		long var1 = 0L;
		long[] var3 = p_76127_0_;
		int var4 = p_76127_0_.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			long var6 = var3[var5];
			var1 += var6;
		}

		return (double) var1 / (double) p_76127_0_.length;
	}

	public static boolean func_180185_a(float p_180185_0_, float p_180185_1_) {
		return abs(p_180185_1_ - p_180185_0_) < 1.0E-5F;
	}

	public static int func_180184_b(int p_180184_0_, int p_180184_1_) {
		return (p_180184_0_ % p_180184_1_ + p_180184_1_) % p_180184_1_;
	}

	/**
	 * the angle is reduced to an angle between -180 and +180 by mod, and a 360
	 * check
	 */
	public static float wrapAngleTo180_float(float p_76142_0_) {
		p_76142_0_ %= 360.0F;

		if (p_76142_0_ >= 180.0F) {
			p_76142_0_ -= 360.0F;
		}

		if (p_76142_0_ < -180.0F) {
			p_76142_0_ += 360.0F;
		}

		return p_76142_0_;
	}

	/**
	 * the angle is reduced to an angle between -180 and +180 by mod, and a 360
	 * check
	 */
	public static double wrapAngleTo180_double(double angle) {
		angle %= 360.0D;

		if (angle >= 180.0D) {
			angle -= 360.0D;
		}

		if (angle < -180.0D) {
			angle += 360.0D;
		}

		return angle;
	}

	/**
	 * parses the string as integer or returns the second parameter if it fails
	 */
	public static int parseIntWithDefault(String s, int defaultInt) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable e) {
			return defaultInt;
		}
	}

	/**
	 * parses the string as integer or returns the second parameter if it fails.
	 * this value is capped to par2
	 */
	public static int parseIntWithDefaultAndMax(String s, int def, int max) {
		return Math.max(max, parseIntWithDefault(s, def));
	}

	/**
	 * parses the string as double or returns the second parameter if it fails.
	 */
	public static double parseDoubleWithDefault(String s, double def) {
		try {
			return Double.parseDouble(s);
		} catch (Throwable var4) {
			return def;
		}
	}

	public static double parseDoubleWithDefaultAndMax(String s, double def, double max) {
		return Math.max(max, parseDoubleWithDefault(s, def));
	}

	/**
	 * Returns the input value rounded up to the next highest power of two.
	 */
	public static int roundUpToPowerOfTwo(int i) {
		int im1 = i - 1;
		im1 |= im1 >> 1;
		im1 |= im1 >> 2;
		im1 |= im1 >> 4;
		im1 |= im1 >> 8;
		im1 |= im1 >> 16;
		return im1 + 1;
	}

	/**
	 * Is the given value a power of two? (1, 2, 4, 8, 16, ...)
	 */
	private static boolean isPowerOfTwo(int i) {
		return i != 0 && (i & i - 1) == 0;
	}

	/**
	 * Uses a B(2, 5) De Bruijn sequence and a lookup table to efficiently
	 * calculate the log-base-two of the given value. Optimized for cases where
	 * the input value is a power-of-two. If the input value is not a
	 * power-of-two, then subtract 1 from the return value.
	 */
	private static int calculateLogBaseTwoDeBruijn(int i) {
		i = isPowerOfTwo(i) ? i : roundUpToPowerOfTwo(i);
		return multiplyDeBruijnBitPosition[(int) (i * 125613361L >> 27) & 31];
	}

	/**
	 * Efficiently calculates the floor of the base-2 log of an integer value.
	 * This is effectively the index of the highest bit that is set. For
	 * example, if the number in binary is 0...100101, this will return 5.
	 */
	public static int calculateLogBaseTwo(int i) {
		return calculateLogBaseTwoDeBruijn(i) - (isPowerOfTwo(i) ? 0 : 1);
	}

	public static int func_154354_b(int p_154354_0_, int p_154354_1_) {
		if (p_154354_1_ == 0) {
			return 0;
		} else if (p_154354_0_ == 0) {
			return p_154354_1_;
		} else {
			if (p_154354_0_ < 0) {
				p_154354_1_ *= -1;
			}

			int var2 = p_154354_0_ % p_154354_1_;
			return var2 == 0 ? p_154354_0_ : p_154354_0_ + p_154354_1_ - var2;
		}
	}

	public static int func_180183_b(float p_180183_0_, float p_180183_1_, float p_180183_2_) {
		return func_180181_b(floor_float(p_180183_0_ * 255.0F), floor_float(p_180183_1_ * 255.0F), floor_float(p_180183_2_ * 255.0F));
	}

	public static int func_180181_b(int p_180181_0_, int p_180181_1_, int p_180181_2_) {
		int var3 = (p_180181_0_ << 8) + p_180181_1_;
		var3 = (var3 << 8) + p_180181_2_;
		return var3;
	}

	public static int func_180188_d(int p_180188_0_, int p_180188_1_) {
		int var2 = (p_180188_0_ & 16711680) >> 16;
		int var3 = (p_180188_1_ & 16711680) >> 16;
		int var4 = (p_180188_0_ & 65280) >> 8;
		int var5 = (p_180188_1_ & 65280) >> 8;
		int var6 = (p_180188_0_ & 255) >> 0;
		int var7 = (p_180188_1_ & 255) >> 0;
		int var8 = (int) ((float) var2 * (float) var3 / 255.0F);
		int var9 = (int) ((float) var4 * (float) var5 / 255.0F);
		int var10 = (int) ((float) var6 * (float) var7 / 255.0F);
		return p_180188_0_ & -16777216 | var8 << 16 | var9 << 8 | var10;
	}

	public static long func_180187_c(int p_180187_0_, int p_180187_1_, int p_180187_2_) {
		long var3 = p_180187_0_ * 3129871 ^ p_180187_2_ * 116129781L ^ p_180187_1_;
		var3 = var3 * var3 * 42317861L + var3 * 11L;
		return var3;
	}

	public static UUID randomUUID(Random rng) {
		long msb = rng.nextLong() & -61441L | 16384L;
		long lsb = rng.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
		return new UUID(msb, lsb);
	}

	/**
	 * Calculates the yaw rotation (x axis, field of view) angle from 2
	 * locations (e1, e2). <br>
	 *
	 * @param x Location 1's x position.
	 * @param z Location 1's z position.
	 * @param x1 Location 2's x position.
	 * @param z1 Location 2's z position.
	 * @return The yaw.
	 */
	public static float calcYaw(double x, double z, double x1, double z1) {
		double d = x - x1;
		double d1 = z - z1;
		return (float) (((Math.atan2(d1, d) * 180D) / Math.PI) + 90) % 360;
	}

	/**
	 * Calculates the pitch rotation (y axis, elevation) angle from 2 locations
	 * (e1, e2). <br>
	 *
	 * @param x Location 1's x position.
	 * @param y Location 1's y position.
	 * @param z Location 1's z position.
	 * @param x1 Location 2's x position.
	 * @param y1 Location 2's y position.
	 * @param z1 Location 2's z position.
	 * @return The pitch.
	 */
	public static float calcPitch(double x, double y, double z, double x1, double y1, double z1) {
		double dis1 = y1 - (y + 1);
		double dis2 = Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(z1 - z, 2));
		return (float) ((Math.atan2(dis2, dis1) * 180D) / Math.PI) - 90F;
	}
}
package com.wbx.algorithm._09_布隆过滤器;

/**
 * 布隆过滤器：
 *
 * 应用场景
 * 1.Goolge在BigTable中就使用了BloomFilter，以避免在硬盘中寻找不存在的条目。
 * 2.重点：爬虫的网址判重系统：用爬虫抓取网页时对网页url去重也需要用到BloomFilter。
 * 3.网页黑名单系统
 * 4.垃圾邮件过滤系统
 *
 * 5.重点：解决缓存穿透问题 ：https://www.cnblogs.com/lingyejun/p/10087135.html
 *   我们可以提前将真实正确的商品Id，在添加完成之后便加入到过滤器当中，每次再进行查询时，先确认要查询的Id是否在过滤器当中，如果不在，则说明Id为非法Id，则不需要进行后续的查询步骤了。
 * @author ad
 */
public class BloomFilter<T> {

	/**
	 * 二进制向量的长度(一共有多少个二进制位)
	 */
	private int bitSize;

	/**
	 * 二进制向量
	 */
	private long[] bits;

	/**
	 * 哈希函数的个数
	 */
	private int hashSize;
	
	/**
	 * @param n 数据规模
	 * @param p 误判率, 取值范围(0, 1)
	 */
	public BloomFilter(int n, double p) {
		if (n <= 0 || p <= 0 || p >= 1) {
			throw new IllegalArgumentException("wrong n or p");
		}
		
		double ln2 = Math.log(2);
		// 求出二进制向量的长度（按照公式算）-->增大数组长度可以减少hash冲突
		bitSize = (int) (- (n * Math.log(p)) / (ln2 * ln2));

		// 求出哈希函数的个数（按照公式算）-->增多hash函数可以减少重复的概率
		hashSize = (int) (bitSize * ln2 / n);

		// bits数组的长度
		bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];

		// 每一页显示100条数据, pageSize
		// 一共有999999条数据, n
		// 请问有多少页 pageCount = (n + pageSize - 1) / pageSize
	}
	
	/**
	 * 添加元素
	 * @param value 要添加的值
	 * @return 是否对数组有影响
	 */
	public boolean put(T value) {
		nullCheck(value);

		// 利用value生成2个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
	
		boolean result = false;
		for (int i = 1; i <= hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			//数据溢出就会小于0
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			} 
			// 生成一个二进位的索引
			int index = combinedHash % bitSize;
			// 设置index位置的二进位为1
			if (set(index)) {
				result = true;
			}
			
			//   101010101010010101
			// | 000000000000000100   1 << index
			//   101010111010010101
		}
		return result;
	}
	
	/**
	 * 判断一个元素是否存在
	 * @return false代表一定不存在，true代表可能存在
	 */
	public boolean mightContain(T value) {
		nullCheck(value);
		// 利用value生成2个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
	
		for (int i = 1; i <= hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			} 
			// 生成一个二进位的索引
			int index = combinedHash % bitSize;
			// 查询index位置的二进位是否为0
			if (!get(index)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 设置index位置的二进位为1
	 */
	private boolean set(int index) {
		//找字节是：左-->右
		long value = bits[index / Long.SIZE];
		//在找到的自己中改变bit是：右-->左
		int bitValue = 1 << (index % Long.SIZE);
		bits[index / Long.SIZE] = value | bitValue;
		return (value & bitValue) == 0;
	}
	
	/**
	 * 查看index位置的二进位的值
	 * @return true代表1, false代表0
	 */
	private boolean get(int index) {
		long value = bits[index / Long.SIZE];
		return (value & (1 << (index % Long.SIZE))) != 0;
	}
	
	private void nullCheck(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null.");
		}
	}
}

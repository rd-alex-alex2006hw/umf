package com.intel.umf;

import java.util.Map;

import com.intel.umf.MetadataStream.VideoSegment;

public class FormatEncrypted extends Format {

	public FormatEncrypted(Format format, Encryptor encryptor) {
		super( n_FormatEncrypted(format.nativeObj, encryptor.nativeObj) );
	}

	@Override
	public String store(
			MetadataSet set,
			MetadataSchema[] schemas,
			VideoSegment[] segments,
			Stat[] stats,
			Map<String, String> attribs
			) {
		long[] schemasAddrs = null, segmentsAddrs = null, statsAddrs = null;
		String[] attribNames = null, attribVals = null;
		
		if(schemas != null && schemas.length > 0) {
			schemasAddrs = new long[schemas.length];
			int i=0;
			for(MetadataSchema s : schemas)
				schemasAddrs[i++] = s.nativeObj;
		}
		if(segments != null && segments.length > 0) {
			segmentsAddrs = new long[segments.length];
			int i=0;
			for(MetadataStream.VideoSegment seg : segments)
				segmentsAddrs[i++] = seg.nativeObj;
		}
		if(stats != null && stats.length > 0) {
			statsAddrs = new long[stats.length];
			int i=0;
			for(Stat st : stats)
				statsAddrs[i++] = st.nativeObj;
		}
		if(attribs != null && attribs.size()>0) {
			attribNames = new String[attribs.size()];
			attribVals  = new String[attribs.size()];
			int i=0;
			for (Map.Entry<String, String> entry : attribs.entrySet())
			{
				attribNames[i] = entry.getKey();
				attribVals [i] = entry.getValue();
				i++;
			}
		}
		
		return n_store(nativeObj, (set==null ? 0 : set.nativeObj), schemasAddrs, segmentsAddrs, statsAddrs, attribNames, attribVals);
	}

	@Override
	public Data parse(String text) {
		final int maxAttribCount = 10;
		String[] attribNames = new String[maxAttribCount], attribVals = new String[maxAttribCount]; 
		long[] objects = n_parse(nativeObj, text, attribNames, attribVals);
		return new Data(objects, attribNames, attribVals);
	}

	private native static long n_FormatEncrypted(long format, long encryptor);
    private native static String n_store(long nativeObj, long set, long[] schemas, long[] segments, long[] stats, String[] attribNames, String[] attribVals);
    private native static long[] n_parse(long nativeObj, String text, String[] attribNames, String[] attribVals);
}

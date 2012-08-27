package com.facebook.presto;

import com.google.common.base.Preconditions;

import java.util.Iterator;

public class UncompressedBlockStream
        implements BlockStream<UncompressedValueBlock>
{
    private final TupleInfo info;
    private final Iterable<UncompressedValueBlock> source;

    public UncompressedBlockStream(TupleInfo info, Iterable<UncompressedValueBlock> source)
    {
        Preconditions.checkNotNull(info, "info is null");
        Preconditions.checkNotNull(source, "source is null");

        this.info = info;
        this.source = source;
    }

    @Override
    public Iterator<UncompressedValueBlock> iterator()
    {
        return source.iterator();
    }

    @Override
    public TupleInfo getTupleInfo()
    {
        return info;
    }

    @Override
    public Cursor cursor()
    {
        return new UncompressedCursor(info, source.iterator());
    }
}

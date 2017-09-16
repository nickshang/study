package java7.concurrency;

/**
 * 微博更新操作
 */
public final class Update implements Comparable<Update> {

    /**
     * 作者
     */
    private final Author author;

    /**
     * 微博内容
     */
    private final String updateText;

    /**
     * 创建时间
     */
    private final long createTime;

    /**
     * 获取作者
     *
     * @return
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * 获取微博内容
     *
     * @return
     */
    public String getUpdateText() {
        return updateText;
    }

    /**
     * 构造器私有-》需要利用静态内部类构造器模式
     *
     * @param b_
     */
    private Update(Builder b_) {
        author = b_.author;
        updateText = b_.updateText;
        createTime = b_.createTime;
    }

    /**
     * 静态内部类构造器模式
     */
    public static class Builder implements ObjBuilder<Update> {
        public long createTime;
        private Author author;
        private String updateText;

        public Builder author(Author author_) {
            author = author_;
            return this;
        }

        public Builder updateText(String updateText_) {
            updateText = updateText_;
            return this;
        }

        public Builder createTime(long createTime_) {
            createTime = createTime_;
            return this;
        }

        /**
         * 创建微博操作对象
         *
         * @return
         */
        public Update build() {
            return new Update(this);
        }
    }

    public int compareTo(Update other_) {
        if (null == other_)
            throw new NullPointerException();

        return (int) (other_.createTime - this.createTime);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + (int) (createTime ^ (createTime >>> 32));
        result = prime * result
                + ((updateText == null) ? 0 : updateText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Update other = (Update) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (createTime != other.createTime)
            return false;
        if (updateText == null) {
            if (other.updateText != null)
                return false;
        } else if (!updateText.equals(other.updateText))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Update [author=" + author + ", updateText=" + updateText
                + ", createTime=" + createTime + "]";
    }


    public static void main(String[] args) {

    }
}
